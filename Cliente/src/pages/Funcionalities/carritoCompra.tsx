import { createContext, useContext, useState } from "react";

export interface ProductoCarrito {
    id: number;
    nombre: string;
    precio: number;
    cantidad: number;
}

interface CarritoContextType {
    carrito: ProductoCarrito[];
    agregarProducto: (producto: ProductoCarrito) => void;
    eliminarProducto: (id: number) => void;
    vaciarCarrito: () => void;
}

const EstadoCarrito = createContext<CarritoContextType | null>(null);

export function Carrito({ children }: { children: React.ReactNode }) {
    const [carrito, setCarrito] = useState<ProductoCarrito[]>([]);

    const agregarProducto = (producto: ProductoCarrito) => {
        setCarrito(prev => {
            const existente = prev.find(p => p.id === producto.id);
            if (existente) {
                return prev.map(p =>
                    p.id === producto.id
                        ? { ...p, cantidad: p.cantidad + producto.cantidad }
                        : p
                );
            }
            return [...prev, producto];
        });
    };

    const eliminarProducto = (id: number) => {
        setCarrito(prev => prev.filter(p => p.id !== id));
    };

    const vaciarCarrito = () => setCarrito([]);

    return (
        <EstadoCarrito.Provider
            value={{ carrito, agregarProducto, eliminarProducto, vaciarCarrito }}
        >
            {children}
        </EstadoCarrito.Provider>
    );
}

export function useCarrito() {
    const context = useContext(EstadoCarrito);
    if (!context) {
        throw new Error("useCarrito debe usarse dentro de CarritoProvider");
    }
    return context;
}
