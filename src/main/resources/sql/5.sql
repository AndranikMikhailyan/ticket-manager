select p.first_name, p.last_name, p.patronymic
from tickets t
inner join routes r on t.route_id = r.id
inner join passengers p on t.passenger_id = p.id
where r.arrival_city = 'Ставрополь' and t.departure_date between '2019-12-01' and '2020-07-01'
group by p.id
having sum(t.price) > 15000;