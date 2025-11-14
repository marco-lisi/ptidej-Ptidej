package padl.visitor;

import padl.kernel.IConstituent;

public interface ITraverser {
    public int getNodesVisited();
    public int getNodesPruned();
    public void walk(final IConstituent pRoot, final IVisitor pVisitor);
}
