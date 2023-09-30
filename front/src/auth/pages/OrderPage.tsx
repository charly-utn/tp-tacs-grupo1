import { Order } from "../../interfaces/Order"

export const OrderPage = (order: Order) => {
  return (
    <li className="list-group-item border border-dark p-3 mb-3">
        <div className="d-flex justify-content-between align-items-center">
            <div>
                <p className="mb-1">Nombre: {order.name}</p>
                <p className="mb-1">Orden: {order.id}</p>
            </div>
        </div>
    </li>
  )
}
