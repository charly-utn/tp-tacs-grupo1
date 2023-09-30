import { useContext } from "react";
//import "./Product.css";
import { OrdersContext } from "../context/OrdersContext";

function Product({
    name = "Nombre del Producto",
    description = "Descripción del Producto",
    price = 2000,
    image = "https://i.postimg.cc/NfR2yhNs/image-equilibrium.jpg"
}) {

  //const {handlerAddProduct} = useContext(OrdersContext) TODO: implementar handlerAddProduct

  return (
    <>
    {/*<div className="card-container">
        <a href="/" className="product-image-container">
          <img className="product-image" src={image} alt="Product Image"/>
        </a>
        <main className="main-content">
          <h1 className="product-title">{name}</h1>
          <p className="product-description">{description}</p>
          <div className="flex-row">
            <div className="price-container">
              
              <h2 className="price">${price}</h2>
            </div>

          </div>
        </main>
        <div className="card-attribute">
          <span><a href="#">+ Agregar al Pedido</a></span>
        </div>
  </div>*/}
    <div className="col">
      <div className="card">
        <img src={image} className="card-img-top" alt="Product Image" />
        <div className="card-body">
            <h5 className="card-title">{name}</h5>
            <p className="card-text">{description}</p>
            <p className="card-text">$ {price}</p>
            <a href="#" className="card-link">Agregar al Pedido</a>
        </div>
      </div>
    </div>
    </>
  );
}

export default Product;
