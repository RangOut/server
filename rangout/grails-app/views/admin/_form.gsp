<%@ page import="br.com.rangout.Admin" %>



<div class="fieldcontain ${hasErrors(bean: adminInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="admin.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="email" required="" value="${adminInstance?.email}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: adminInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="admin.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${adminInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: adminInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="admin.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="password" name="password" required="" value="${adminInstance?.password}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: adminInstance, field: 'restaurant', 'error')} required">
	<label for="restaurant">
		<g:message code="admin.restaurant.label" default="Restaurant" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="restaurant" name="restaurant.id" from="${br.com.rangout.Restaurant.list()}" optionKey="id" required="" value="${adminInstance?.restaurant?.id}" class="many-to-one"/>

</div>

