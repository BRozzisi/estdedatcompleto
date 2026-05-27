package jerarquicas.catedraAG;
import lineales.dinamicas.*;


public class ArbolGen {
    private NodoGen raiz;

    public ArbolGen(){
        raiz=null;
    }
    //insertar en arbol vacío requiere null para insertar en la raiz.
    public boolean insertar(Object e,Object p){
        NodoGen n=null;
        if (p == null){
            n = new NodoGen(e,this.raiz,null);
            this.raiz = n;
        }
        else{
            NodoGen np = buscarNodoGen(this.raiz, p);
            if(np !=null){
                n = new NodoGen(e,null,null);
                n.setHermanoDerecho(np.getHijoIzquierdo());
                np.setHijoIzquierdo(n);

            }

        }
        return n!=null;
    }

    private NodoGen buscarNodoGen(NodoGen n, Object p){
        NodoGen np=null;
        if (n!=null){
            if (n.getElem().equals(p)){np=n;}
            else {
                NodoGen hijo=n.getHijoIzquierdo();
                while (hijo!=null && np==null){
                    np=buscarNodoGen(hijo,p);
                    hijo = hijo.getHermanoDerecho();
                }

            }
        }
        return np;
    }

    //insertar ignora la posiciòn si el arbol està vacìo, siempre inserta en la raiz en este caso. 
    //insertar en un arbol no vacìo requiere que el padre exista.
    //la posiciòn de la raìz es 1 en preorden.
    public boolean insertarPorPosicion(Object e,int p){
        return true;
    }

    public boolean pertenece(Object e){
        return true;
    }

    public boolean esVacio(){
        return this.raiz==null;
    }

    public Object padre(Object e){
        return null;
    }

    public int altura(){
        return 1;
    }

    public int nivel(Object e){
        return 0;
    }

    public boolean ancestros(Object e,Object p){
        return true;
    }

    public boolean clone(Object e,Object p){
        return true;
    }

    public boolean vaciar(Object e,Object p){
        return true;
    }

    public boolean listarPreorden(Object e,Object p){
        return true;
    }

    public boolean listarPostorden(Object e,Object p){
        return true;
    }

    public boolean listarInorden(Object e,Object p){
        return true;
    }

    public Lista porNiveles(){
        return null;
    }

    public String toString(){
        return toStingAux(this.raiz);
    }

    private String toStingAux(NodoGen n){
        String s="";
        if(n!=null){
            s+=n.getElem().toString()+":";
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo!=null){
                s+=hijo.getElem().toString();
                hijo = hijo.getHermanoDerecho();
                if (hijo!=null) s+=",";
            }
            hijo=n.getHijoIzquierdo();
            while (hijo!=null){
                s+='\n' + toStingAux(hijo);
                hijo=hijo.getHermanoDerecho();
            }
        }
        return s;

    }

}
