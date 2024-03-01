import { Component, OnDestroy, OnInit, inject, input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Cart, LineItem, Order } from '../models';
import { CartStore } from '../cart.store';
import { Observable, Subscription, lastValueFrom, reduce } from 'rxjs';

@Component({
  selector: 'app-confirm-checkout',
  templateUrl: './confirm-checkout.component.html',
  styleUrl: './confirm-checkout.component.css'
})
export class ConfirmCheckoutComponent implements OnInit, OnDestroy{

  // TODO Task 3

  private fb = inject(FormBuilder)
  detailsForm!: FormGroup

  private cartStore = inject(CartStore)

  sub! :Subscription

  cart$!: Observable<LineItem[]>
  cart! : Cart
  totalQuantity: number = 0

  ngOnInit(): void {
      this.detailsForm = this.createDetailsForm()
      console.info("confirm checkout Oninit")
      this.cart$ = this.cartStore.getProducts
      this.cart$.subscribe(value => { console.info("Confirm Checkout value: ", value)})

      this.sub= this.cartStore.getProducts.subscribe(
        (value)=>{
          this.cart.lineItems = value
        }
      )
      

      lastValueFrom(this.cart$)
      .then(value =>{ 
        // console.info( "Value: " ,value)
        value.map(v=>{
         let total = v.price*v.quantity
           total += this.totalQuantity})
          console.info("Total quantity: ", this.totalQuantity)
      })
        // .then(value => {
        //   console.info("Price * quantity: ",value)
        // })
                
  }

  ngOnDestroy(): void {
      this.sub.unsubscribe
  }

  createDetailsForm():FormGroup{
    return this.fb.group({
      name: this.fb.control<string>("", [Validators.required]),
      address: this.fb.control<string>("", [Validators.required, Validators.minLength(3)]),
      priority: this.fb.control<boolean>(false),
      comments: this.fb.control<string>(""),

    })
  }

  processForm(){

    const inputOrder = this.detailsForm.value
    console.info("Input order value: ", inputOrder)

    
    const order: Order={
      name: inputOrder['name'],
      address: inputOrder['address'],
      priority: inputOrder['priority'],
      comments: inputOrder['comments'],
      cart: this.cart
    }


  }

}
