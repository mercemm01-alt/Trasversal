export interface Producto{
    idProducto: number;
    nombre: string;
    precio: number;
    descripcion: string;
    imagen: string;
    ingredientes: number[];
    alergenos: string[];
    tipo: "PASTELERIA" | "PANADERIA";
}