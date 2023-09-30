import { NavLink } from "react-router-dom"
import { Order } from "../../interfaces/Order"
import { useState } from 'react';
import { Clipboard } from 'react-bootstrap-icons';

export const OrderPage = (order: Order) => {
  const [showSnackbar, setShowSnackbar] = useState(false);

  const copyToClipboard = () => {
    navigator.clipboard.writeText(order.id)
      .then(() => {
        setShowSnackbar(true);
        setTimeout(() => {
          setShowSnackbar(false); // Ocultar el Snackbar después de un tiempo
        }, 3000); // Duración del Snackbar en pantalla
      })
      .catch((error) => {
        console.error('Error al copiar al portapapeles: ', error);
      });
  };

  return (
     <li className="list-group-item border border-dark p-3 mb-3">
      <div className="d-flex justify-content-between align-items-center">
        <div>
          <p className="mb-1">Nombre: {order.name}</p>
          <div className="d-flex align-items-center"> {/* Agregamos un contenedor flex para alinear el botón junto a order.id */}
            <p className="mb-1">Orden: {order.id}</p>
              <button
                className="btn btn-secondary mx-2" // Reducimos el espacio entre el texto y el botón
                onClick={copyToClipboard}
              >
              <Clipboard/> Copiar
            </button>
          </div>
        </div>
        <div>
          <NavLink className="btn btn-success" to={"/items?order_id=" + order.id}>
            Ver Orden
          </NavLink>
          <button
            className="btn btn-danger mx-3"
            // Agregar handler onclick para cerrar el pedido
          >
            Cerrar Pedido
          </button>
        </div>
      </div>
      {showSnackbar && (
        <div className="position-absolute top-0 start-50 translate-middle-x alert alert-success" role="alert">
          Código copiado al portapapeles
        </div>
      )}
    </li>
  )
}
