package bancoDados;

import bancoDados.Gasto;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-12-08T20:13:13", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(TipoGasto.class)
public class TipoGasto_ { 

    public static volatile SingularAttribute<TipoGasto, Integer> idTipoGasto;
    public static volatile SingularAttribute<TipoGasto, String> descricaoTipo;
    public static volatile CollectionAttribute<TipoGasto, Gasto> gastoCollection;

}