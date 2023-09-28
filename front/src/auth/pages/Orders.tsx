import React, { useEffect, useState } from 'react';
import { Order } from "../../interfaces/Order";
import { getOrdersByUser } from '../../services/UsersApiClient';

export const Orders = () => {
    const [orders, setOrders] = useState<Order[]>([]);

    useEffect(() => {
        const fetchOrders = async () => {
            try {
                const response = await getOrdersByUser(); // Utiliza la función actualizada
                const listOrder:Order[] = [];
                for(let i = 0; i < response.OrdersIds.length; i++) {
                    const order: Order = {
                        id: response.OrdersIds[i],
                        name: 'Orden' + i,
                        items: []
                    };
                    listOrder.push(order);
                }
                setOrders(listOrder);
            } catch (error) {
                console.error('Error al obtener los pedidos:', error);
            }
        };
        fetchOrders();
    }, []);

    return (
        <div className="container mt-4">
            <h1 className="mb-4">Detalle de Órdenes</h1>
            <ul className="list-group">
                {orders.map((order) => (
                    <li key={order.id} className="list-group-item border border-dark p-3 mb-3">
                        <div className="d-flex justify-content-between align-items-center">
                            <div>
                                <p className="mb-1">Nombre: {order.name}</p>
                                <p className="mb-1">Orden: {order.id}</p>
                            </div>
{/*                            <Button variant="primary" onClick={() => handleAction(order.id)}>
                                Realizar Acción
                            </Button>*/}
                        </div>
                    </li>
                ))}
            </ul>
        </div>
    );
};