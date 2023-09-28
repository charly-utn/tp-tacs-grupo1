import { Route, Routes } from 'react-router-dom';
import { Login, Register } from '../auth' 
import { Products } from '../views'

export const AppRouter = () => {
  return (
    <>
        <Routes>
        <Route path="/*" element={<Login/>} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/productos" element={<Products />} />
        </Routes>
    </>
  )
}
