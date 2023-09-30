import { useContext, useEffect } from "react";
import { OrdersContext } from "../context/OrdersContext";
import { ItemOrder } from "../interfaces/ItemOrder"
import { Product } from "../components/Product"
import { AlertOk } from "../components/SweetAlert";

export const Products = () => {
    const {products, getProducts}: {products: [ItemOrder], getProducts: any} = useContext(OrdersContext);
    const queryString = window.location.search;
    const params = new URLSearchParams(queryString);

    useEffect(() => { 
        getProducts(params.get('order_id'))
    }, [])

    //Problema: si hay un producto con descripción demasiado larga, se estira toda la card.
    //@TODO: despues implementar un "ver más..." (si los caracteres son >38 x ejemplo)

    // Problema: si son mas de 4 productos, siguen apareciendo en la misma row. Habría que crear 1 nueva row por cada 4 productos.
    return (
        <div>
            <h1>Productos</h1>
            <div className="flex-row">{
                products.map(io => (
                    <Product
                        key={io.item.id}
                        id={io.item.id}
                        name={io.item.name}
                        description={'Descripción del Producto'}
                        price={io.item.price}
                        picture={io.item.picture}
                        quantity={io.quantity}
                        />
                ))
            }
            </div>
        </div>
    );
}
