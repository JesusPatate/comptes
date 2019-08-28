new Vue({
  el: '#app',

  data: {
    accounts: [],
    transactions: []
  },

  computed: {
    assetAccounts() {
      return this.accounts.filter(account => account.type === 'ASSET');
    },

    displayTransactions() {
      return this.transactions.length > 0;
    }
  },

  methods: {
      fetchAccounts() {
        axios.get('http://localhost:8080/accounts')
        .then(response => {
          response.data
            .sort((a1, a2) => a1.id - a2.id)
            .forEach(account => {
              this.accounts.push(account);
            })
        });
      },

      showThransactions(accountId) {
        axios.get('http://localhost:8080/accounts/' + accountId + '/transactions')
        .then(response => {
          this.transactions.splice(0, this.transactions.length);
          response.data.forEach(transaction => {
            this.transactions.push(transaction);
          });
        })
      },

      format(number) {
        return number.toLocaleString(undefined, {
          minimumFractionDigits: 2
        });
      }
  },

  mounted: function() {
    this.fetchAccounts();
  }
});
