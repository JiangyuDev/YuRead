package pers.jiangyu.yuread.base;

public abstract class BaseModel<P extends BasePresenter> {

    protected P presenter;

    public BaseModel( P presenter){
        this.presenter = presenter;
    }
}
