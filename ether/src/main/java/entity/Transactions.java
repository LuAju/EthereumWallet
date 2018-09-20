package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="transaction")
public class Transactions {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="account")
	private String account;
	
	@Column(name="transHash")
	private String transHash;
	
	@Column(name="transCate")
	private String transCate;
	
	
	
	public Transactions(String account, String transHash, String transCate) {
		super();
		this.account = account;
		this.transHash = transHash;
		this.transCate = transCate;
	}
	public Transactions() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getTransHash() {
		return transHash;
	}
	public void setTransHash(String transHash) {
		this.transHash = transHash;
	}
	public String getTransCate() {
		return transCate;
	}
	public void setTransCate(String transCate) {
		this.transCate = transCate;
	}
	@Override
	public String toString() {
		return "Transactions [id=" + id + ", account=" + account + ", transHash=" + transHash + ", transCate="
				+ transCate + "]";
	}
	
	
}
