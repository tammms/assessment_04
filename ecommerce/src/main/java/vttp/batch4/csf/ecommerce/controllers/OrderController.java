package vttp.batch4.csf.ecommerce.controllers;


import java.io.StringReader;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import vttp.batch4.csf.ecommerce.models.Cart;
import vttp.batch4.csf.ecommerce.models.LineItem;
import vttp.batch4.csf.ecommerce.models.Order;
import vttp.batch4.csf.ecommerce.services.PurchaseOrderService;

@Controller
@RequestMapping(path="/api" ,produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

  @Autowired
  private PurchaseOrderService poSvc;

  // IMPORTANT: DO NOT MODIFY THIS METHOD.
  // If this method is changed, any assessment task relying on this method will
  // not be marked
  @PostMapping(path="/order", consumes= MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<String> postOrder(@RequestBody String orderInput) {

    // TODO Task 3
    System.out.println("\nthis is ok!" );
    JsonReader reader = Json.createReader(new StringReader(orderInput));
    JsonObject json = reader.readObject();
    
    System.out.println("\n\npayload from angular" + json);
    //   private Date date = new Date();
  // private String name;
  // private String address;
  // private boolean priority;
  // private String comments;
  // private Cart cart = new Cart();

    Order order = new Order();
    order.setName(json.getString("name"));
    order.setAddress(json.getString("address"));
    order.setPriority(json.getBoolean("priority"));
    order.setComments(json.getString("comments"));

    JsonArray itemsJson =json.getJsonObject("cart")
                              .getJsonArray("lineItems");
   
    //     private String productId;
  // private String name;
  // private int quantity;
  // private float price;

    for(int i=0; i<itemsJson.size(); i++){
      System.out.println("\n>> Item"+ itemsJson.get(i));
      JsonObject itemObj = itemsJson.getJsonObject(i);
      LineItem it = new LineItem();
      it.setProductId(itemObj.getString("prodId"));
      it.setName(itemObj.getString("name"));
      it.setQuantity(itemObj.getInt("quantity"));
      it.setPrice(Float.parseFloat(itemObj.getString("price")));
    
    }
    
    /*
     * for(let i = 0; i < json.length; i++) {
    let obj = json[i];

    console.log(obj.id);
}
     */

    /*
     {"lineItems":[{"prodId":"65e13cb64deac228b2aba788","quantity":1,"name":"Cheese Slices - Made From Cow Milk 663 g + Cheese Spread - Cream Cheese 100 g","price":710},{"prodId":"65e13cb64deac228b2abafec","quantity":1,"name":"Deodorant Body Spray - Be Delicious Woman EDT","price":5550},{"prodId":"65e13cb64deac228b2abb011","quantity":1,"name":"Eau De Toilette For Men","price":7150}]}
     */
  

  /*
   * payload from angular{"name":"xcxz","address":"csc","priority":false,"comments":"","cart":{"lineItems":[{"prodId":"65e13cb64deac228b2aba788","quantity":1,"name":"Cheese Slices - Made From Cow Milk 663 g + Cheese Spread - Cream Cheese 100 g","price":710}]}}
   * 
   */

  //   // poSvc.createNewPurchaseOrder(null);
	 
	 return ResponseEntity.ok("{}");
  }
}
