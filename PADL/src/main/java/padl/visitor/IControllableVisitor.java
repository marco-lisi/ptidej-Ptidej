package padl.visitor;

import padl.kernel.IConstituent;

public interface IControllableVisitor extends IVisitor {
    VisitDecision beforeChildren(IConstituent p_node);
    default void afterChildren(IConstituent p_node) { }
}
