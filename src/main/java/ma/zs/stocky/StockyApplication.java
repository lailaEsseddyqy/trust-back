package ma.zs.stocky;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.openfeign.EnableFeignClients;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ma.zs.stocky.zynerator.security.bean.*;
import ma.zs.stocky.zynerator.security.common.AuthoritiesConstants;
import ma.zs.stocky.zynerator.security.service.facade.*;


import ma.zs.stocky.zynerator.security.bean.User;
import ma.zs.stocky.zynerator.security.bean.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.mail.MessagingException;

@SpringBootApplication
//@EnableFeignClients
@EnableScheduling

public class StockyApplication {
    public static ConfigurableApplicationContext ctx;

    @Autowired
    private EmailSenderService senderService;

    public static void main(String[] args) {
        ctx=SpringApplication.run(StockyApplication.class, args);
    }
   /* @EventListener(ApplicationReadyEvent.class)
    public void triggerMail() throws MessagingException {
        senderService.sendSimpleEmail("lailaesseddyqy@gmail.com",
                "This is email body",
                "This is email subject");
    }*/

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    public static ConfigurableApplicationContext getCtx() {
        return ctx;
    }

    @Bean
    public CommandLineRunner demo(UserService userService, RoleService roleService, ModelPermissionService modelPermissionService, ActionPermissionService actionPermissionService, ModelPermissionUserService modelPermissionUserService) {
    return (args) -> {
        if(true){


        // ModelPermissions
        List<ModelPermission> modelPermissions = new ArrayList<>();
        addPermission(modelPermissions);
        modelPermissions.forEach(e -> modelPermissionService.create(e));
        // ActionPermissions
        List<ActionPermission> actionPermissions = new ArrayList<>();
        addActionPermission(actionPermissions);
        actionPermissions.forEach(e -> actionPermissionService.create(e));


        

		// User Responsableachat
		User userForResponsableachat = new User("responsableachat");
		userForResponsableachat.setPassword("123");
		// Role Responsableachat
		Role roleForResponsableachat = new Role();
		roleForResponsableachat.setAuthority(AuthoritiesConstants.RESPONSABLEACHAT);
		roleForResponsableachat.setCreatedAt(LocalDateTime.now());
		Role roleForResponsableachatSaved = roleService.create(roleForResponsableachat);
		RoleUser roleUserForResponsableachat = new RoleUser();
		roleUserForResponsableachat.setRole(roleForResponsableachatSaved);
		if (userForResponsableachat.getRoleUsers() == null)
			userForResponsableachat.setRoleUsers(new ArrayList<>());

		userForResponsableachat.getRoleUsers().add(roleUserForResponsableachat);
		if (userForResponsableachat.getModelPermissionUsers() == null)
			userForResponsableachat.setModelPermissionUsers(new ArrayList<>());


        userForResponsableachat.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        userService.create(userForResponsableachat);

        

		// User Chefprojet
		User userForChefprojet = new User("chefprojet");
		userForChefprojet.setPassword("123");
		// Role Chefprojet
		Role roleForChefprojet = new Role();
		roleForChefprojet.setAuthority(AuthoritiesConstants.CHEFPROJET);
		roleForChefprojet.setCreatedAt(LocalDateTime.now());
		Role roleForChefprojetSaved = roleService.create(roleForChefprojet);
		RoleUser roleUserForChefprojet = new RoleUser();
		roleUserForChefprojet.setRole(roleForChefprojetSaved);
		if (userForChefprojet.getRoleUsers() == null)
			userForChefprojet.setRoleUsers(new ArrayList<>());

		userForChefprojet.getRoleUsers().add(roleUserForChefprojet);
		if (userForChefprojet.getModelPermissionUsers() == null)
			userForChefprojet.setModelPermissionUsers(new ArrayList<>());


        userForChefprojet.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        userService.create(userForChefprojet);

        

		// User Comptable
		User userForComptable = new User("comptable");
		userForComptable.setPassword("123");
		// Role Comptable
		Role roleForComptable = new Role();
		roleForComptable.setAuthority(AuthoritiesConstants.COMPTABLE);
		roleForComptable.setCreatedAt(LocalDateTime.now());
		Role roleForComptableSaved = roleService.create(roleForComptable);
		RoleUser roleUserForComptable = new RoleUser();
		roleUserForComptable.setRole(roleForComptableSaved);
		if (userForComptable.getRoleUsers() == null)
			userForComptable.setRoleUsers(new ArrayList<>());

		userForComptable.getRoleUsers().add(roleUserForComptable);
		if (userForComptable.getModelPermissionUsers() == null)
			userForComptable.setModelPermissionUsers(new ArrayList<>());


        userForComptable.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        userService.create(userForComptable);

        

		// User Admin
		User userForAdmin = new User("admin");
		userForAdmin.setPassword("123");
		// Role Admin
		Role roleForAdmin = new Role();
		roleForAdmin.setAuthority(AuthoritiesConstants.ADMIN);
		roleForAdmin.setCreatedAt(LocalDateTime.now());
		Role roleForAdminSaved = roleService.create(roleForAdmin);
		RoleUser roleUserForAdmin = new RoleUser();
		roleUserForAdmin.setRole(roleForAdminSaved);
		if (userForAdmin.getRoleUsers() == null)
			userForAdmin.setRoleUsers(new ArrayList<>());

		userForAdmin.getRoleUsers().add(roleUserForAdmin);
		if (userForAdmin.getModelPermissionUsers() == null)
			userForAdmin.setModelPermissionUsers(new ArrayList<>());


        userForAdmin.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        userService.create(userForAdmin);

            }
        };
    }




    private static String fakeString(String attributeName, int i) {
        return attributeName + i;
    }

    private static Long fakeLong(String attributeName, int i) {
        return  10L * i;
    }
    private static Integer fakeInteger(String attributeName, int i) {
        return  10 * i;
    }

    private static Double fakeDouble(String attributeName, int i) {
        return 10D * i;
    }

    private static BigDecimal fakeBigDecimal(String attributeName, int i) {
        return  BigDecimal.valueOf(i*1L*10);
    }

    private static Boolean fakeBoolean(String attributeName, int i) {
        return i % 2 == 0 ? true : false;
    }
    private static LocalDateTime fakeLocalDateTime(String attributeName, int i) {
        return LocalDateTime.now().plusDays(i);
    }


    private static void addPermission(List<ModelPermission> modelPermissions) {
        modelPermissions.add(new ModelPermission("EtatAchat"));
        modelPermissions.add(new ModelPermission("ProjetCollaborateur"));
        modelPermissions.add(new ModelPermission("DomaineInterventionCollaborateur"));
        modelPermissions.add(new ModelPermission("Projet"));
        modelPermissions.add(new ModelPermission("Achat"));
        modelPermissions.add(new ModelPermission("CategorieArticle"));
        modelPermissions.add(new ModelPermission("Article"));
        modelPermissions.add(new ModelPermission("Facture"));
        modelPermissions.add(new ModelPermission("ProjetArticle"));
        modelPermissions.add(new ModelPermission("TypeFacture"));
        modelPermissions.add(new ModelPermission("DomaineIntervention"));
        modelPermissions.add(new ModelPermission("Piece"));
        modelPermissions.add(new ModelPermission("Collaborateur"));
        modelPermissions.add(new ModelPermission("EtatArticle"));
        modelPermissions.add(new ModelPermission("AchatDetail"));
        modelPermissions.add(new ModelPermission("Societe"));
        modelPermissions.add(new ModelPermission("Marque"));
        modelPermissions.add(new ModelPermission("Fournisseur"));
        modelPermissions.add(new ModelPermission("Client"));
        modelPermissions.add(new ModelPermission("EtatAvancement"));
        modelPermissions.add(new ModelPermission("User"));
        modelPermissions.add(new ModelPermission("ModelPermission"));
        modelPermissions.add(new ModelPermission("ActionPermission"));
    }

    private static void addActionPermission(List<ActionPermission> actionPermissions) {
        actionPermissions.add(new ActionPermission("list"));
        actionPermissions.add(new ActionPermission("create"));
        actionPermissions.add(new ActionPermission("delete"));
        actionPermissions.add(new ActionPermission("edit"));
        actionPermissions.add(new ActionPermission("view"));
        actionPermissions.add(new ActionPermission("duplicate"));
    }


}


