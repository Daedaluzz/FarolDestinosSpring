class Masks {
    input = {};
    masks = {
      sms: { mask: "(99)99999-9999" },
      cpf: { mask: "999.999.999-99" }
    };
    constructor(id) {
      this.input = document.querySelector(`#${id}`);
    }
  
    putMask(type) {
      this.type = type;
      this.input.placeholder = this.masks[type].mask;
      this.input.setAttribute("maxlength", this.masks[type].mask.length);
      this.input.onkeydown = (e) => {
        if (
          this.input.value.length < this.masks[type].mask.length &&
          !(e.key === "Backspace" || e.key === "Delete")
        ) {
          while (isNaN(parseInt(this.masks[type].mask[this.input.value.length])))
            this.input.value += this.masks[type].mask[this.input.value.length];
          console.log(this.masks[type].mask[this.input.value.length]);
        }
        console.log(e.key);
      };
    }
    removeMask() {
      this.value = this.input.value;
      const result = Array.from(
        new Set(this.masks[this.type].mask.replace(/\d+/g, ""))
      );
      result.forEach(
        (c) => (this.value = this.value.replace(new RegExp("\\" + c, "g"), ``))
      );
      return this.value;
    }
  }
  
  const mask = new Masks("mask")
  mask.putMask("sms")

  document.addEventListener('keydown', function(event) { //evento de precionar uma tecla
    if(event.keyCode != 46 && event.keyCode != 8){//verificar se a tecla precionada nao e um backspace e delete
      var i = document.getElementById("CPF").value.length; //tamanho do input
      if (i === 3 || i === 7) //ponto no terceiro e setimo indice
        document.getElementById("CPF").value = document.getElementById("CPF").value + ".";
      else if (i === 11) //tralo no decimo primeiro indice
        document.getElementById("CPF").value = document.getElementById("CPF").value + "-";
    }
  });