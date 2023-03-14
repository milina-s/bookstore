import React, { Component } from 'react'
import { HiTrash } from "react-icons/hi"

export class Cart extends Component {
    render() {
        return (
            <div className='item'>
                <img src={this.props.item.image} />
                <h3>{this.props.item.title}</h3>
                <p>{this.props.item.author}</p>
                <b>{this.props.item.price} uan</b>
                <HiTrash className='delete-from-cart' onClick={() => this.props.onDelete(this.props.item.id)} />
            </div>
        )
    }
}

export default Cart