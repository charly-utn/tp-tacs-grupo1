import { useContext, useEffect, useState } from 'react';
import { Order } from "../../interfaces/Order";
import { OrdersContext } from '../../context/OrdersContext';
import { OrderPage } from './OrderPage';
import { OrderRequest } from '../../interfaces/OrderRequest';

export const Orders = () => {
    const {orders, getOrders, handleCreateOrder} = useContext(OrdersContext)

    useEffect(() => { 
        const fetch = async () => {
            await getOrders()
        }
        fetch()
    }, [])

    //const [orders, setOrders] = useState<Order[]>([]);

    const fakeOrderRequest: OrderRequest = {
        items: [{
            id: 'abcd',
            quantity: 2    
        }]
    }

    const onCreateOrder = async() => {
        await handleCreateOrder(fakeOrderRequest)
    }

    return (
        <div className="container mt-4">
            <h1 className="mb-4">Detalle de Órdenes</h1>
            {
                orders.length == 0 ? (
                    <>
                    <p className="my-3">Usted no posee pedidos. Cree un pedido para agregar productos</p>
                    </>
                ) :
                <ul className="list-group">{
                    orders.map((o: Order) => (
                        <OrderPage key={o.id} id={o.id} name={o.name} items={o.items}/>
                    ))
                }
                </ul>
            }
            <button className="btn btn-success my-3"
                onClick={onCreateOrder}>Crear Pedido</button>
        </div>
    );
};