public class FiltroOscuro extends Filtro{
    public FiltroOscuro(String nombre){
        super(nombre);
    }

    @Override
    public void aplicar(ImagenOF imagen) {
        int alto = imagen.getHeight();
        int ancho = imagen.getWidth();
        for(int y = 0; y < alto; y++) {
            for(int x = 0; x < ancho; x++) {
                imagen.setPixel(x, y, imagen.getPixel(x,y).darker());
            }
        }
    }
}
