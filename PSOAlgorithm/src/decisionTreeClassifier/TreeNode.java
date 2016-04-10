package decisionTreeClassifier;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

	private List<TreeNode> childs;
	private Attribute attribute;
	
	public TreeNode(Attribute attribute){
		if(attribute.getValues()!=null){
			this.childs=new ArrayList<TreeNode>(attribute.getValues().size());
			for(int i=0;i<attribute.getValues().size();i++)
				this.childs.add(null);
		}
		else{
			this.childs= new ArrayList<TreeNode>(1);
			this.childs.add(null);
		}
		this.attribute=attribute;
	}
	public void addTreeNode(TreeNode treeNode,String valueName){
		int index=this.attribute.indexValue(valueName);
		this.childs.set(index, treeNode);
	}
	
	public int totalChilds(){
		return this.childs.size();
	}
	
	public TreeNode getChild(int index){
		return this.childs.get(index);
	}
	
	public Attribute getAttribute(){
		return this.attribute;
	}
	
	public TreeNode getChildByBranchName(String branchName){
		int index = this.attribute.indexValue(branchName);
		return this.childs.get(index);
	}
}
