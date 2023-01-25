import React, { Component } from 'react'
import { HiOutlineShoppingCart } from "react-icons/hi"

export class Item extends Component {
    render() {
        return (
            <div className='item'>
                <img src={this.props.item.image} />
                <h3>{this.props.item.title}</h3>
                <p>{this.props.item.author}</p>
                <b>{this.props.item.price} uan</b>
                <div className='add-to-cart' onClick={() => this.props.onAdd(this.props.item)}>
                    <HiOutlineShoppingCart className='icon' />
                    Купити</div>
            </div>
        )
    }
}

export default Item