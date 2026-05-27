package jerarquicas.catedraAG;

public class NodoGen {
    private Object elem;
    private NodoGen hijoIzquierdo;
    private NodoGen hermanoDerecho;

    public NodoGen(Object e,NodoGen hei, NodoGen hder){
        elem = e;
        hijoIzquierdo=hei;
        hermanoDerecho=hder;
    }
   
    public Object getElem(){
        return elem;
    }

    public NodoGen getHijoIzquierdo(){
        return this.hijoIzquierdo;
    }

    public NodoGen getHermanoDerecho(){
        return this.hermanoDerecho;
    }

    public void setElem(Object e){
        this.elem=e;
    }

    public void setHijoIzquierdo(NodoGen hei){
        this.hijoIzquierdo=hei;
    }

    public void setHermanoDerecho(NodoGen hder){
        this.hermanoDerecho=hder;
    }


}
