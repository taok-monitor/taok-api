package br.com.taok.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name="lancamento")
public class Launcher {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "historico_id_seq")
	@SequenceGenerator(name = "historico_id_seq", sequenceName = "historico_id_seq")
	@Column(name = "id")
	private Integer id;
	
	@Column(name="id_municipio")
	private Integer municipio;
	private String identificador;
	private String orgao;
	private Date data;
	private BigDecimal valor;
	
	@Column(name="cpfcnpj_favorecido")
	private String cpfcnpjFavorecido;

	@Column(name="nome_favorecido")
	private String nomeFavorecido;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Integer municipio) {
		this.municipio = municipio;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public String getOrgao() {
		return orgao;
	}
	public void setOrgao(String orgao) {
		this.orgao = orgao;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getCpfcnpjFavorecido() {
		return cpfcnpjFavorecido;
	}
	public void setCpfcnpjFavorecido(String cpfcnpjFavorecido) {
		this.cpfcnpjFavorecido = cpfcnpjFavorecido;
	}
	public String getNomeFavorecido() {
		return nomeFavorecido;
	}
	public void setNomeFavorecido(String nomeFavorecido) {
		this.nomeFavorecido = nomeFavorecido;
	}
	
	@Override
	public String toString() {
		return "Lancamento [id=" + id + ", municipio=" + municipio + ", identificador=" + identificador + ", orgao="
				+ orgao + ", data=" + data + ", valor=" + valor + ", cpfcnpjFavorecido=" + cpfcnpjFavorecido
				+ ", nomeFavorecido=" + nomeFavorecido + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Launcher other = (Launcher) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
