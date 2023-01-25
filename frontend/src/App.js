import React, { useState, useEffect } from "react";
import Header from "./components/Header";
import Footer from "./components/Footer";
import Items from "./components/Items";
import axios from 'axios';

const src = "http://localhost:8081/api/v1/bookstore/get_all_books"

function App() {
  const [books, setBooks] = useState([]);
  const [cart, setCart] = useState([]);

  useEffect(() => {
    axios
      .get(src)
      .then(data => {
        setBooks(data.data)
      })
  })

  const deleteFromCart = (id) => {
    setCart(cart.filter((el) => el.id !== id), () => {
      console.log(cart)
    })
  }

  const addToCart = (item) => {
    setCart([...cart, item])
  }

  return (
    <div className="wrapper">
      <Header cart={cart} onDelete={deleteFromCart} />
      <Items items={books} onAdd={addToCart} />
      <Footer />
      <h1>HEllo</h1>
    </div>
  );
}


export default App;
