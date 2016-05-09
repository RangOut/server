package com.herokuapp.rangout

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.*
import grails.validation.ValidationException

@Transactional(readOnly = true)
@Secured(['isFullyAuthenticated()'])
class EstablishmentController {

    def springSecurityService

    static allowedMethods = [list: "GET", getOrders: "GET", getMenu: "GET", addItemMenu: "POST", updateItemMenu: "PUT"]

    @Secured(["permitAll"])
    def list() {
        JSON.use('estList') {
            render(status: 200, contentType: 'application/json') {[
                    establishments: Establishment.listOrderByName(),
                    status: 'ok'
            ]}
        }
    }

    @Secured(["ROLE_EMPLOYEE"])
    def getOrders() {
        Employee employee = springSecurityService.currentUser

        JSON.use('ordList') {
            render(status: 200, contentType: 'application/json') {[
                    orders: [],
                    status: 'ok'
            ]}
        }
    }

    @Secured(["ROLE_EMPLOYEE"])
    def getMenu() {
        Employee employee = springSecurityService.currentUser

        JSON.use('menList') {
            render(status: 200, contentType: 'application/json') {[
                    menu: employee.establishment.menu,
                    status: 'ok'
            ]}
        }
    }

    @Secured(["ROLE_EMPLOYEE"])
    def addItemMenu() {
        def object = request.JSON
        Employee employee = springSecurityService.currentUser

        List<Item> saved = []
        def establishment = employee.establishment

        object['items']?.each { item ->
            String name = item['name']
            String description = item['description']
            String category = item['category']

            double price = item['price']
            Set<String> ingredients = item['ingredients']

            def menuItem = new Item(name: name, description: description, category: category, price: price, ingredients: ingredients, establishment: establishment)

            menuItem.validate()
            if (menuItem.hasErrors()) {
                return forward(controller: 'error', action: 'badRequest', params: ['message': 'Fail to save all items'])
            } else {
                saved.add(menuItem)
            }
        }
        if (saved.isEmpty()) {
            return forward(controller: 'error', action: 'badRequest', params: ['message': 'Unable to save a empty item'])
        } else  {
            saved.each { item ->
                item.save(failOnError: true)
            }
            return forward(controller: 'establishment', action: 'getMenu')
        }
    }

    def updateItemMenu() {
        def object = request.JSON
        def item = Item.findById(params?.getLong('itemId', 0))

        if (item == null) {
            return forward(controller: 'error', action: 'notFound', params: ['message': 'Item NOT FOUND'])
        }
        String name = object['name']
        String description = object['description']
        String category = object['category']

        Double price = object['price']
        Boolean available = object['available']
        Set<String> ingredients = object['ingredients']

        if (name != null)
            item.setName(name)
        if (description != null)
            item.setDescription(description)
        if (category != null)
            item.setCategory(category)
        if (price != null)
            item.setPrice(price)
        if (available != null)
            item.setAvailable(available)
        if (ingredients != null)
            item.setIngredients(ingredients)

        try {
            item.save(failOnError: true)
            JSON.use('menList') {
                render(status: 202, contentType: 'application/json') {[
                        item: item,
                        status: 'ok'
                ]}
            }
        } catch (ValidationException e) {
            return forward(controller: 'error', action: 'badRequest', params: ['message': e.message])
        }
    }
}