package com.sean.user_service

import com.sean.user_service.entity.Role
import com.sean.user_service.service.RoleService
import com.sean.user_service.service.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.annotation.Order

//@SpringBootApplication
class AuthApplicationBak {
	@Bean
	@Order(1)
	fun run(usrSrv: UserService, roleSrv: RoleService): CommandLineRunner {
		return CommandLineRunner { args ->
			roleSrv.saveOne(Role(Role.RoleName.root.name))
			roleSrv.saveOne(Role(Role.RoleName.admin.name))
			roleSrv.saveOne(Role(Role.RoleName.superuser.name))
			roleSrv.saveOne(Role(Role.RoleName.user.name))
		}
	}

//	@Autowired
//	private lateinit var appContext: ApplicationContext

//	@EventListener(ApplicationReadyEvent::class)
//	fun dataFactory() {
//		DataFactory.createData()
//	}

//	@EventListener(ApplicationReadyEvent::class)
//	fun displayAllBeans() {
//		val allBeanNames: Array<String> = appContext.beanDefinitionNames
//		for (beanName in allBeanNames) {
//			println(beanName)
//		}
//	}
}

//fun main(args: Array<String>) {
//	runApplication<AuthApplication>(*args)
//}



