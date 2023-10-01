import { useContext, useEffect, useState } from "react";
import { OrdersContext } from "../context/OrdersContext";
import { ItemOrder } from "../interfaces/ItemOrder"
import { Product } from "../components/Product"
import { AlertOk } from "../components/SweetAlert";
import { useParams, useSearchParams } from "react-router-dom";
import { findAll } from "../services/ProductsService";

export const Products = () => {
    const defaultResponse: ItemOrder[] = []
    //const {orderId} = useParams() // By path param, i.e.: /items/:order_id
    const [searchParams] = useSearchParams()
    const [products, setProducts] = useState(defaultResponse);

    const getProducts = async (id:string | null)=> {
        const p = await findAll(id)
        setProducts(p)
    }
    
    useEffect(() => { 
        getProducts(searchParams.get('order_id'))
        
    }, [])

    //Problema: si hay un producto con descripción demasiado larga, se estira toda la card.
    //@TODO: despues implementar un "ver más..." (si los caracteres son >38 x ejemplo)

    // Problema: si son mas de 4 productos, siguen apareciendo en la misma row. Habría que crear 1 nueva row por cada 4 productos.

    return (
        <div className="p-4">
            <h1>Productos</h1>
            <hr></hr>
            <div className="row rows-cols-1 row-cols-md-6">{
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
