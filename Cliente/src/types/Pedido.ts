export interface Pedido {
    idPedido: number;
    fechaPedido: string;
    fechaEntrega: string;
    estado: string;
    total: number;
    nombreCliente: string;
    apellidosCliente: string;
}