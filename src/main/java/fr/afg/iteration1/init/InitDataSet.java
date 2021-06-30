package fr.afg.iteration1.init;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import fr.afg.iteration1.entity.*;
import fr.afg.iteration1.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import fr.afg.iteration1.service.ProductService;
import fr.afg.iteration1.service.UserService;


@Profile("initData")
@Component
@Transactional
public class InitDataSet {

    @Autowired
    UserService userService;
    @Autowired
    PurchaseOrderService purchaseOrderService;

    @Autowired
    ProductService productService;


    @PostConstruct
    public void initLovedDatas() {

        Company company1 = new Company();
        company1.setId(1L);
        Company company2 = new Company();
        company2.setId(3L);
        Company company3 = new Company();
        company3.setId(3L);

        User userTest = new User();
        userTest.setEmail("customer");
        userTest.setPassword("pass");
        userTest.setRoles("ROLE_CUSTOMER");
        userTest.setActive(true);
        userTest.setFirstName("Romain");
        userTest.setLastName("Drogoul");
        userTest.setCompany(company1);
        userService.saveUser(userTest);

        User userTest2 = new User();
        userTest2.setEmail("email2");
        userTest2.setPassword("pass2");
        userTest2.setRoles("ROLE_CUSTOMER");
        userTest2.setActive(true);
        userTest2.setFirstName("Anthony");
        userTest2.setLastName("Davis");
        userTest2.setCompany(company2);
        userService.saveUser(userTest2);

        User preparator = new User();
        preparator.setEmail("p");
        preparator.setPassword("p");
        preparator.setRoles("ROLE_PREPARATOR");
        preparator.setActive(true);
        preparator.setFirstName("Fabien");
        preparator.setLastName("Olanier");
        userService.saveUser(preparator);

        User logistic = new User();
        logistic.setEmail("logistic");
        logistic.setPassword("pass");
        logistic.setRoles("ROLE_LOGISTIC");
        logistic.setActive(true);
        logistic.setFirstName("Romain");
        logistic.setLastName("Lauret");
        userService.saveUser(logistic);

        Product product1 = new Product();
        product1.setId(74L);

        Product product2 = new Product();
        product2.setId(93L);

        Product product3 = new Product();
        product3.setId(88L);

        Product product4 = new Product();
        product4.setId(26L);

        Product product5 = new Product();
        product5.setId(99L);


        List<CommandLine> commandLines1 = new ArrayList<CommandLine>();
        List<CommandLine> commandLines2 = new ArrayList<CommandLine>();
        List<CommandLine> commandLines3 = new ArrayList<CommandLine>();

        CommandLine commandLine1 = new CommandLine();
        commandLine1.setDesiredQuantity(11f);
        commandLine1.setProduct(product1);
        commandLine1.setActivePrice(75f);

        CommandLine commandLine2 = new CommandLine();
        commandLine2.setDesiredQuantity(22f);
        commandLine2.setProduct(product2);
        commandLine2.setActivePrice(60f);

        CommandLine commandLine3 = new CommandLine();
        commandLine3.setDesiredQuantity(33f);
        commandLine3.setProduct(product3);
		commandLine3.setActivePrice(40f);

        CommandLine commandLine4 = new CommandLine();
        commandLine4.setDesiredQuantity(44f);
        commandLine4.setProduct(product4);
        commandLine4.setActivePrice(9f);

        CommandLine commandLine5 = new CommandLine();
        commandLine5.setDesiredQuantity(55f);
        commandLine5.setProduct(product5);
        commandLine5.setActivePrice(15f);

        CommandLine commandLine6 = new CommandLine();
        commandLine6.setDesiredQuantity(55f);
        commandLine6.setProduct(product5);
        commandLine6.setActivePrice(15f);

        commandLines1.add(commandLine1);
        commandLines1.add(commandLine2);
        commandLines2.add(commandLine3);
        commandLines2.add(commandLine4);
        commandLines2.add(commandLine5);
        commandLines3.add(commandLine6);

        PurchaseOrder order1 = new PurchaseOrder();
        order1.setDeliveryDate(LocalDate.now());
        order1.setCreator(userTest);
        order1.setLines(commandLines1);
        commandLine1.setPurchaseOrder(order1);
        commandLine2.setPurchaseOrder(order1);
        purchaseOrderService.savePurchaseOrder(order1);

        PurchaseOrder order2 = new PurchaseOrder();
        order2.setDeliveryDate(LocalDate.now());
        order2.setCreator(userTest2);
        order2.setLines(commandLines2);
        commandLine3.setPurchaseOrder(order2);
        commandLine4.setPurchaseOrder(order2);
        commandLine5.setPurchaseOrder(order2);
        purchaseOrderService.savePurchaseOrder(order2);

        PurchaseOrder order3 = new PurchaseOrder();
        order3.setDeliveryDate(LocalDate.now());
        order3.setCreator(userTest);
        order3.setLines(commandLines3);
        commandLine6.setPurchaseOrder(order3);
        purchaseOrderService.savePurchaseOrder(order3);

    }

}
