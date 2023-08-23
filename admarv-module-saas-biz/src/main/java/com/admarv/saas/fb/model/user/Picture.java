package com.admarv.saas.fb.model.user;

import java.io.Serializable;

/**
 * 
 * @author liuliu
 *
 */
public class Picture  implements Serializable  {
    
    private static final long serialVersionUID = 5123047075354411420L;
    
    private Data data;

    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Picture [data=" + data + "]";
    }
}
