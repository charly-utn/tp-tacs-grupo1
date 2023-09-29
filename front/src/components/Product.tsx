import "./Product.css";
//<img src="https://i.postimg.cc/T1F1K0bW/Ethereum.png" alt="Ethereum" className="small-image"/>
function Product({
    name = "Nombre del Producto",
    description = "Descripción del Producto",
    price = "2000",
    image = "https://i.postimg.cc/NfR2yhNs/image-equilibrium.jpg"
}) {
    return (

  <div className="card-container">
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
   </div>
    
    );
}

export default Product;
