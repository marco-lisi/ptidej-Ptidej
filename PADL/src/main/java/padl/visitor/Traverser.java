package padl.visitor;

import padl.kernel.IConstituent;

public abstract class Traverser implements ITraverser {
    private final IChildrenProvider childrenProvider;
    private int nodesVisited = 0;
    private int nodesPruned = 0;

    Traverser (IChildrenProvider pProvider) {
        assert pProvider != null;
        this.childrenProvider = pProvider;

    }
    @Override
    public int getNodesVisited() {
        return nodesVisited;
    }

    void updateNodesVisited(int updateNum) {
        nodesVisited += updateNum;
    }

    @Override
    public int getNodesPruned() {
        return nodesPruned;
    }

    void updateNodesPruned(int updateNum) {
        nodesPruned += updateNum;
    }


    void resetStatistics() {
        nodesVisited = 0;
        nodesPruned = 0;
    }
    public abstract void walk(IConstituent pRoot, IVisitor pVisitor);
    public IChildrenProvider getChildrenProvider() {
        return childrenProvider;
    }

}
