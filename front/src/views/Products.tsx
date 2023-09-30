import { useParams } from "react-router-dom";
import Product from "../components/Product";
import { useContext, useEffect, useState } from "react";
import { OrdersContext } from "../context/OrdersContext";
import { Item } from "../interfaces/Item";
import { ItemOrder } from "../interfaces/ItemOrder";

export const Products = () => {
    const {products, getProducts, getProductsByOrder} = useContext(OrdersContext)

    const [itemOrderSelected, setItemOrderSelected] = useState<ItemOrder[]>([])

    const {orderId} = useParams()

    useEffect(() => { 
        const fetch = async () => {
            await getProducts()

            if(orderId) {
                const itemOrder: ItemOrder[] = await getProductsByOrder(orderId);
                setItemOrderSelected(itemOrder)
            }
        }
        fetch()
    }, [orderId])

    //Problema: si hay un producto con descripción demasiado larga, se estira toda la card.
    //@TODO: despues implementar un "ver más..." (si los caracteres son >38 x ejemplo)

    let items: Item[]
    if(!orderId) {
        items = products.map((p: {item: Item}) => p.item)
    } else {
        items = itemOrderSelected.map((io) => io.item)
    }
    //const items: [Item] = products.map((p: {item: Item}) => p.item)
    //const items: Item[] = itemOrderSelected.map((io) => io.item)

    // Problema: si son mas de 4 productos, siguen apareciendo en la misma row. Habría que crear 1 nueva row por cada 4 productos.
    return (
        <div className="container">
            <h1 className="my-3">Productos{orderId ? ` de ${orderId}` : ''}</h1>
            <div className="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-3">
            {
                items.map(item => (
                    <Product
                        key={item.id}
                        name={item.name}
                        //description={item.name}
                        price={item.price}
                        image={item.picture}
                        />
                ))
            }
            </div>
        </div>
    );
}
