package vttp.batch4.csf.ecommerce.repositories;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vttp.batch4.csf.ecommerce.models.Cart;
import vttp.batch4.csf.ecommerce.models.LineItem;
import vttp.batch4.csf.ecommerce.models.Order;

@Repository
public class PurchaseOrderRepository {

  @Autowired
  private JdbcTemplate template;

  // private String productId;
  // private String name;
  // private int quantity;
  // private float price;

  public static final String CREATE_NEW_PURCHASE_ORDER = """
    insert into purchase_order(order_id, date, name, address, priority, comments)
    values (?, ?, ?, ?, ?, ?)
      """;

  public static final String INSERT_LINE_ITEMS = """
    insert into purchase_order(order_id, product_id, name, quantity, price)
    values (?, ?, ?, ?, ?)
      
      """;

  // IMPORTANT: DO NOT MODIFY THIS METHOD.
  // If this method is changed, any assessment task relying on this method will
  // not be marked
  // You may only add Exception to the method's signature
  @Transactional(rollbackFor = InputException.class)
  public void create(Order order) throws InputException {
    // TODO Task 3

    Cart orderCart = order.getCart();
    List<LineItem> itemList = orderCart.getLineItems();
    Integer itemCount = itemList.size();
    Integer count = 0;

    try {

     Boolean orderResult = template.update(CREATE_NEW_PURCHASE_ORDER,
                                        order.getOrderId(),
                                        order.getDate(),
                                        order.getName(),
                                        order.getAddress(),
                                        order.getPriority(),
                                        order.getComments()) >0;

    
    for (LineItem li : itemList){
      
      if( template.update(CREATE_NEW_PURCHASE_ORDER,
      order.getOrderId(),
      li.getProductId(),
      li.getName(),
      li.getQuantity(),
      li.getPrice())!=1){
      }
      count += 1;

      Boolean itemResult = count == itemCount;
      if ((itemResult && orderResult)!= true){
        throw new InputException("input failed");
      }

    }
      
    } catch (InputException ex) {
      System.out.println("----- exception occured");
      throw ex;
    }

    
  }
}
