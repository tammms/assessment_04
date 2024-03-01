import { Component, OnInit, inject } from '@angular/core';
import {Observable, lastValueFrom} from 'rxjs';
import {Router} from '@angular/router';
import { CartStore } from './cart.store';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {

  // NOTE: you are free to modify this component

  private router = inject(Router)
  private cartStore = inject(CartStore)

  itemCount!: number

  ngOnInit(): void {
    console.info("App component Oninit")
    this.cartStore.itemCountEvent.subscribe(
      value =>{
        console.info("Value of Subscription: ", value)
        this.itemCount = value
      }
    )
  }

  isInvalidCart():boolean{
    return this.itemCount>=1
  }

  checkout(): void {
    this.router.navigate([ '/checkout' ])
  }
}
