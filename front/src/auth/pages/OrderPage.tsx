import { NavLink } from "react-router-dom"
import { Order } from "../../interfaces/Order"

export const OrderPage = (order: Order) => {
  return (
    <li className="list-group-item border border-dark p-3 mb-3">
        <div className="d-flex justify-content-between align-items-center">
            <div>
                <p className="mb-1">Nombre: {order.name}</p>
                <p className="mb-1">Orden: {order.id}</p>
            </div>
            <div>
              <NavLink className="btn btn-success" to={"/items?order_id" + order.id}>
                Ver Orden
              </NavLink>
              <button
                className="btn btn-danger mx-3"> {/*Agregar handler onClick */}
                Cerrar Pedido
              </button>
            </div>
        </div>

    </li>
  )
}
