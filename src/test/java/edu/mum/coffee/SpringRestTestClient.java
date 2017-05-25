/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.coffee;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Person;
import java.net.URI;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author awalaa
 */
public class SpringRestTestClient {
    
    public static final String REST_SERVICE_URI = "http://localhost:8080/";
     
    /* GET */
    @SuppressWarnings("unchecked")
    private static void listAllOrders(){
        System.out.println("Testing listAllOrder API-----------");
         
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Order>>  orders = restTemplate.getForObject(REST_SERVICE_URI+"orderWS/", List.class);
         
        if(orders!=null){
            orders.forEach((order) -> {
                System.out.println("Order : id="+order.get("id")+", Date="+order.get("orderDate")
                        +", Person="+order.get("person"));
//                +", order lines="+order.get("orderLines")
//                        +", Total Amount=" + order.get());
            });
        }else{
            System.out.println("No order exist----------");
        }
    }
     
//    /* GET */
//    private static void getUser(){
//        System.out.println("Testing getUser API----------");
//        RestTemplate restTemplate = new RestTemplate();
//        User user = restTemplate.getForObject(REST_SERVICE_URI+"/user/1", User.class);
//        System.out.println(user);
//    }
//     
    /* POST */
    private static void createOrder() {
        System.out.println("Testing create Order API----------");
        RestTemplate restTemplate = new RestTemplate();
        Order order = new Order();
        order.setOrderDate(new Date());
        Person person = restTemplate.getForObject(REST_SERVICE_URI+"/personWS/1",Person.class);
        order.setPerson(person);
        
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/orderWS/", order, Order.class);
        System.out.println("Location : "+uri.toASCIIString());
    }
// 
//    /* PUT */
//    private static void updateUser() {
//        System.out.println("Testing update User API----------");
//        RestTemplate restTemplate = new RestTemplate();
//        User user  = new User(1,"Tomy",33, 70000);
//        restTemplate.put(REST_SERVICE_URI+"/user/1", user);
//        System.out.println(user);
//    }
// 
//    /* DELETE */
//    private static void deleteUser() {
//        System.out.println("Testing delete User API----------");
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.delete(REST_SERVICE_URI+"/user/3");
//    }
// 
// 
//    /* DELETE */
//    private static void deleteAllUsers() {
//        System.out.println("Testing all delete Users API----------");
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.delete(REST_SERVICE_URI+"/user/");
//    }
 
    public static void main(String args[]){
        
        System.out.println("/////////////////////////////////////////////////////////////\n                   TESTING REST API SERVICE \n//////////////////////////////////////////////////////////");
        listAllOrders();
//        getUser();
        createOrder();
//        listAllUsers();
//        updateUser();
//        listAllUsers();
//        deleteUser();
//        listAllUsers();
//        deleteAllUsers();
//        listAllUsers();
    }
}
    

