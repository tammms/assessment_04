import { Injectable } from "@angular/core";
import { ComponentStore } from "@ngrx/component-store";
import { LineItem } from "./models";
import { Subject } from "rxjs";

export interface CartSlice{
    LineItems: LineItem[]
}

const INIT_STORE :CartSlice ={
    LineItems:[]
}

@Injectable()
// TODO Task 2
// Use the following class to implement your store

export class CartStore extends ComponentStore<CartSlice>{
    
    itemCountEvent = new Subject <number>()
    
    constructor(){
        super(INIT_STORE)
    }

    readonly addProduct = this.updater<LineItem>(
        (slice: CartSlice, inputLineItem: LineItem)=>{

            return{
                LineItems:[... slice.LineItems, inputLineItem]
            }
        }
    )

    readonly getProducts = this.select<LineItem[]>(
        (slice: CartSlice) => {
            // console.info("Number of LineItems in Cart Store: ", slice.LineItems.length)
            return slice.LineItems
        }
    )

    readonly getItemCount = this.select<number>(
        (slice: CartSlice)=>{
            const items = [... new Set( slice.LineItems.map(obj => obj.prodId))]
            this.itemCountEvent.next(items.length)
            return items.length
        }
    )


    
}
