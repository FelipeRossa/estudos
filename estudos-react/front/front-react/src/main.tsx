import ReactDOM from 'react-dom/client';
import App from './App.tsx';
import './index.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Menu from './components/Menu.tsx';
import "bootswatch/dist/darkly/bootstrap.min.css";
import { BrowserRouter } from 'react-router-dom';

ReactDOM.createRoot(document.getElementById('root')!).render(
  <>
    <BrowserRouter>
      <Menu />
      <div className='container'>
        <App />
      </div>
    </BrowserRouter>


  </>

)
