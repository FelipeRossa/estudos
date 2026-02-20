interface TitlePageProps {
    tituloPagina: string;
    children?: React.ReactNode;
}

const TitlePage: React.FC<TitlePageProps> = (
        {tituloPagina, children}: TitlePageProps
    ) => {
    return (
        <div className="d-flex justify-content-between align-items-end mt-2 pb-3 border-bottom border-2">
            <h1 className='m-0 p-0'>{tituloPagina}</h1>
            {children}
        </div>
    )
}

export default TitlePage;