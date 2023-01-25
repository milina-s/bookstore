import React, { useState } from 'react'
import '../css/header.css';
import { HiShoppingCart, HiUser, HiHeart } from "react-icons/hi"
import Cart from './Cart';

const showCartItems = (props) => {
  let total = 0
  props.cart.forEach(el => {
    total += el.price
  });
  return (<div className='shop-cart'> {
    props.cart.map(el => (
      <Cart key={el.id} item={el} onDelete={props.onDelete} />
    ))}
    <p className='total-sum'>Всього {new Intl.NumberFormat().format(total)} грн</p>
  </div>)
}

const showEmptyCart = () => {
  return <div className='empty-shop-cart'>Кошик пустий</div>
}

export default function Header(props) {
  let [cartOpen, setCartOpen] = useState(false)

  return (
    <header>
      <div>

        {/* <ul className='nav'>
          <li>About us</li>
          <li>Contact</li>
        </ul> */}
        <span className='logo'>Bookshop</span>
        <HiUser className='icon' />
        <HiHeart className='icon' />
        <HiShoppingCart className={`icon ${cartOpen && 'active'}`} onClick={() => setCartOpen(cartOpen = !cartOpen)} />

        {cartOpen && (
          <div>
            {props.cart.length > 0 ? showCartItems(props) : showEmptyCart()}
          </div>
        )}
      </div>
      <div className='presentation'>
      </div>
    </header>
  )
}
