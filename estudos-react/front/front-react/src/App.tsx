import { Route, Routes } from 'react-router-dom';
import './App.css'
import Servico from "./pages/atividades/Servico";
import Cliente from './pages/clientes/Cliente';
import Home from './pages/home/Home';
import ClienteForm from './pages/clientes/ClienteForm';
import PageNotFound from './components/PageNotFound';

 const App: React.FC = () => {

  return (
    <>
      <Routes>
        <Route path='/' element={<Home />}></Route>
        <Route path='/servico/lista' element={<Servico />}></Route>
        <Route path='/cliente/lista' element={<Cliente />}></Route>
        <Route path='/cliente/detalhe/:id?' element={<ClienteForm />}></Route>
        <Route path='*' element={<PageNotFound />}></Route>
      </Routes>
    </>
  )
}

export default App;