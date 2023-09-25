import React, { useEffect, useState } from 'react';
import { Order } from "../../interfaces/Order";
import { getOrdersByUser } from '../../services/UsersApiClient';

export const Orders = () => {
    const [orders, setOrders] = useState<Order[]>([]);

    useEffect(() => {
        const fetchOrders = async () => {
            try {
                const fetchedOrders = await getOrdersByUser(); // Utiliza la función actualizada
                setOrders(fetchedOrders);
            } catch (error) {
                console.error('Error al obtener los pedidos:', error);
            }
        };

        fetchOrders();
    }, []);

    return (
        <div>
            <h1>Detalle de Órdenes</h1>
            <ul>
                {orders.map((order) => (
                    <li key={order.id}>
                        <p>Order: {order.id}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
};