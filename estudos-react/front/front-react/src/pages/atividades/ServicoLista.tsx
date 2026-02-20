import { ServicoListaProps } from "../../model/servicoProps";
import ServicoItem from "./ServicoItem";



const ServicoLista: React.FC<ServicoListaProps> = ({
    servicos,
    getServico,
    handleConfirmModal
}: ServicoListaProps) => {
    return (
        <div className='mt-3'>
            {servicos.map((servico: any) => (

                <ServicoItem
                    key={servico.id}
                    servico={servico}
                    getServico={getServico}
                    handleConfirmModal={handleConfirmModal}
                />
            ))}
        </div>
    )
}
export default ServicoLista;