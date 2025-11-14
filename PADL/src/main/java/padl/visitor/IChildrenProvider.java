package padl.visitor;

import padl.kernel.IConstituent;

public interface IChildrenProvider {
    Iterable<IConstituent> childrenOf(IConstituent p_node);
}
