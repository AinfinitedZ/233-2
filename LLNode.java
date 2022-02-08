/**
 * Simple implementation of Double linked List. 
 * @author dxl746
 */
public class LLNode{
    protected Double value;
    protected LLNode next;
    protected LLNode previous;

    public LLNode(Double value){
        this.value = value;
    }
    
    public LLNode(Double value, LLNode next, LLNode previous){
        this.value = value;
        this.next = next;
        this.previous = previous;
    }

    public LLNode getNext(){
        return this.next;
    }

    public LLNode getPrevious(){
        return this.previous;
    }

    public void setNext(LLNode node){
        this.next = node;
    }

    public void setPrevious(LLNode node){
        this.previous = node;
    }

    public Double getValue(){
        return this.value;
    }

    public void setValue(Double value){
        this.value = value;
    }
}