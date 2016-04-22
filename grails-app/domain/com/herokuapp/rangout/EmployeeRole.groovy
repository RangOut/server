package com.herokuapp.rangout

import org.apache.commons.lang.builder.HashCodeBuilder

class EmployeeRole implements Serializable {

    private static final long serialVersionUID = 1

    Role role
    Employee employee

    static constraints = {
        role validator: { Role r, EmployeeRole er ->
            if(er.employee == null) return

            boolean existing = false
            EmployeeRole.withNewSession {
                existing = EmployeeRole.exists(er.employee.id, r.id)
            }
            if(existing) return 'userRole.exists'
        }
    }

    static mapping = {
        version false
        id composite: ['role', 'employee']
    }

    static EmployeeRole get(long userId, long roleId) {
        EmployeeRole.where {
            employee == Employee.load(userId) && role == Role.load(roleId)
        }.get()
    }

    static boolean exists(long userId, long roleId) {
        EmployeeRole.where {
            employee == Employee.load(userId) && role == Role.load(roleId)
        }.count() > 0
    }

    static EmployeeRole create(Employee employee, Role role, boolean flush=false) {
        def instance = new EmployeeRole(employee: employee, role: role)

        instance.save(flush: flush, insert: true)
        instance
    }

    static boolean remove(Employee e, Role r, boolean flush=false) {
        if (e == null || r == null) return false

        int rowCount = EmployeeRole.where {
            employee == Employee.load(e.id) && role == Role.load(r.id)
        }.deleteAll()

        if (flush) {
            EmployeeRole.withSession {
                it.flush()
            }
        }
        rowCount > 0
    }

    static void removeAll(Employee e, boolean flush=false) {
        if (e == null) return

        EmployeeRole.where {
            employee == Employee.load(e.id)
        }.deleteAll()

        if(flush) {
            EmployeeRole.withSession {
                it.flush()
            }
        }
    }

    static void removeAll(Role r, boolean flush=false) {
        if(r == null) return

        EmployeeRole.where {
            role == Role.load(r.id)
        }.deleteAll()

        if(flush) {
            EmployeeRole.withSession {
                it.flush()
            }
        }
    }

    boolean equals(other) {
        if (!(other instanceof EmployeeRole)) {
            return false
        }

        other.employee?.id == employee?.id && other.role?.id == role?.id
    }

    int hashCode() {
        def builder = new HashCodeBuilder()
        if(employee)
            builder.append(employee.id)
        if(role)
            builder.append(role.id)

        builder.toHashCode()
    }
}
