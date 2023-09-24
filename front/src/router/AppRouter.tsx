import { Route, Routes } from 'react-router-dom';
import { Login, Register } from '../auth' 
export const AppRouter = () => {
  return (
    <>
        <Routes>
        <Route path="/*" element={<Login/>} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        </Routes>
    </>
  )
}
