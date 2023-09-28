import { useNavigate } from "react-router-dom";
import Product from "../components/Product";

export const Products = () => {
    const navigate = useNavigate();
    
    //@TODO: recibir el array de productos del back
    const productList = [
        {
            name:"Hamburguesa",
            description: "Hamburguesa con carne, lechuga y queso.",
            price: "4995",
            image: "https://media.istockphoto.com/id/1309352410/es/foto/hamburguesa-con-queso-con-tomate-y-lechuga-en-tabla-de-madera.webp?s=1024x1024&w=is&k=20&c=eD-BBjoFkwriCJkrPXpl07TrIzLTJs00BciR9oJ9A_g="
        },
        {
            name: "Papas Fritas",
            description: "Porción de Fritas.",
            price: "1890",
            image: "https://images.unsplash.com/photo-1630384060421-cb20d0e0649d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1450&q=80"
        },
        {
            name: "Aros de Cebolla",
            description: "Porción de aros fritos de cebolla",
            price: "1990",
            image: "https://www.paulinacocina.net/wp-content/uploads/2021/12/aros-de-cebolla-fritos-800x534.jpg"
        },
        
    ]
    //Problema: si hay un producto con descripción demasiado larga, se estira toda la card.
    //@TODO: despues implementar un "ver más..." (si los caracteres son >38 x ejemplo)

    const productCards = productList.map(p => {
        return <Product name={p.name} description={p.description} price={p.price} image={p.image}
        />
    })

    // Problema: si son mas de 4 productos, siguen apareciendo en la misma row. Habría que crear 1 nueva row por cada 4 productos.
    return (
        <div>
            <h1>Productos</h1>
            <div className="flex-row">
            {productCards}
            </div>
        </div>
    );
}
