import { useContext, useState } from "react";
import "./Product.css";
import { OrdersContext } from "../context/OrdersContext";
import { Item } from "../interfaces/Item";

const defaultProduct: Item = {
  id: "",
  name: "Nombre del Producto",
  description: "Descripción del Producto",
  price: 2000,
  picture: "https://i.postimg.cc/NfR2yhNs/image-equilibrium.jpg",
  quantity: 0
}


export const Product = (item: Item = defaultProduct ) => {

  //const {handlerAddProduct} = useContext(OrdersContext) TODO: implementar handlerAddProduct
  const [quantity, setQuantity] = useState(item.quantity || 0);
  const [debounceActive, setDebounceActive] = useState(false);

  const handleChange = (event: any) => {
    setQuantity(parseInt(event.target.value));
    if (debounceActive) return;
    setDebounceActive(true);    
    let timeoutId = setInterval( () => {
      clearTimeout(timeoutId);
      setDebounceActive(false);
    }, 1000)
  }

  const handleAddProduct = () => {
    if (debounceActive) return;
    setDebounceActive(true);    
    let timeoutId = setInterval( () => {
      console.log("agregando item");
      clearTimeout(timeoutId);
      setDebounceActive(false);
    }, 500)
    setQuantity(quantity + 1)
  }

  const handleRemoveProduct = () => {
    if (quantity == 0) return;
    if (debounceActive) return;
    setDebounceActive(true);    
    let timeoutId = setInterval( () => {
      console.log("sacando item");
      
      clearTimeout(timeoutId);
      setDebounceActive(false);
    }, 500)
    setQuantity(quantity  - 1)
  }

  return (
    <div className="card-container">
        <a href="/" className="product-image-container">
          <img className="product-image" src={item.picture} alt="Product Image"/>
        </a>
        <main className="main-content">
          <h1 className="product-title">{item.name}</h1>
          <p className="product-description">{item.description}</p>
          <div className="flex-row">
            <div className="price-container">
              
              <h2 className="price">${item.price}</h2>
            </div>

          </div>
        </main>
        <div className="card-attribute">
          <button onClick={handleRemoveProduct} className="btn btn-success p-3 m-4"> - </button>
          
          <div className="input-group mb-3">
            <input type="text"
             onInput={handleChange}
             className="form-control"
             id="quantity"
             name="quantity" 
             value={quantity}
         
             /> 
          </div>


          <button onClick={handleAddProduct} className="btn btn-success p-3 m-4"> + </button>
        </div>
    </div>
  );
}
