import { useNavigate, useParams } from "react-router-dom";
import { useContext, useEffect } from "react";
import { OrdersContext } from "../context/OrdersContext";
import { Item } from "../interfaces/Item";
import { Product } from "../components/Product"

export const Products = () => {
    const navigate = useNavigate();
    const {products, getProducts} = useContext(OrdersContext)
    const { order_id } = useParams()
    useEffect(() => { 
        const fetch = async () => {
            await getProducts(order_id)
        }
        fetch()
    }, [])

    //Problema: si hay un producto con descripción demasiado larga, se estira toda la card.
    //@TODO: despues implementar un "ver más..." (si los caracteres son >38 x ejemplo)

    const items: [Item] = products.map((p: {item: Item}) => p.item)
    console.log('items', items)

    // Problema: si son mas de 4 productos, siguen apareciendo en la misma row. Habría que crear 1 nueva row por cada 4 productos.
    return (
        <div>
            <h1>Productos</h1>
            <div className="flex-row">{
                items.map(item => (
                    <Product
                        key={item.id}
                        id={item.id}
                        name={item.name}
                        description={'Descripción del Producto'}
                        price={item.price}
                        picture={item.picture}
                        quantity={item.quantity}
                        />
                ))
            }
            </div>
        </div>
    );
}
