import React, { useContext, useEffect, useState } from "react";
import { ItemOrder } from "../interfaces/ItemOrder"
import { Product } from "../components/Product"
import { AlertOk } from "../components/SweetAlert";
import { useParams, useSearchParams } from "react-router-dom";
import { findAll } from "../services/ProductsService";
import { findOrders } from "../services/OrdersService";
import {Order} from "../interfaces/Order";
import {OrdersContext} from "../context/OrdersContext";

export const Products = () => {
    const defaultResponse: ItemOrder[] = []
    const [searchParams] = useSearchParams()
    const [products, setProducts] = useState(defaultResponse);
    const {orders, getOrders, selectedOrder} = useContext(OrdersContext)

    useEffect(() => {
        const fetch = async () => {
            await getOrders()
        }
        fetch()
    }, [])

    const getProducts = async (id: string | null) => {
        const p = await findAll(id)
        setProducts(p)
    }

    useEffect(() => {
        getProducts(searchParams.get('order_id'))
    }, []);

    // Actualizar productos cuando cambie el pedido seleccionado
    useEffect(() => {
        if (selectedOrder) {
            getProducts(selectedOrder);
        }
    }, [selectedOrder]);


    // Manejar el cambio en la selección del pedido
/*    const handleOrderChange = (event) => {
        const orderId = event.target.value;
        setSelectedOrder(orderId);
    };*/

    return (
        <div className="container mt-4">
            <div className="row">
                <div className="col-md-6">
                    <h1>Productos</h1>
                    <hr />
                </div>
                <div className="col-md-6">
                    <div className="form-group">
                        <label htmlFor="orderSelect">Pedido:</label>
                        <select
                            className="form-control"
                            id="orderSelect"
                            value={selectedOrder}
/*
                            onChange={handleOrderChange}
*/
                        >
                            <option value="">Seleccionar un pedido...</option>
                            {orders.map((order:Order) => (
                                <option key={order.id} value={order.id}>
                                    {order.id}
                                </option>
                            ))}
                        </select>
                    </div>
                </div>
            </div>
            <div className="row row-cols-1 row-cols-md-6">
                {products.map((io) => (
                    <Product
                        key={io.item.id}
                        id={io.item.id}
                        name={io.item.name}
                        description={"Descripción del Producto"}
                        price={io.item.price}
                        picture={io.item.picture}
                        quantity={io.quantity}
                    />
                ))}
            </div>
        </div>
    );
}
